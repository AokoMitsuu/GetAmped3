	� G (/accs/ice_lance/mdl/ice_lance_shield.kmd��       �V�n�F>��>�B���JR��`y�m�uk[������D�\;��)zhҟK/m����\[�A�q���
�]�%�i��1eq4;��f��ᾡ���v�������b�:�q{�	�OAU;dH�^�u�������3�i_�e>����������uf����mS���Έ)E���!M@p�}�|=ӃRD�KF� �`�dd���F��Ea�12�����5���nm�i��#����H��Ԗ��%Xi�PxD�wM�m�-.�h�����5C<Yġ_��U���${��\�����#m�(ǒl"�J�@� \ O]<āM����j{RA�'T��-�:x*Ya�`�m��J�
�V�K6P�oM�б],�d���)p4�g�.��		�/ul7��o��T�4�4�L����X�$H�Z۩�mX���t%��j�(���Q�������\�E�m�������8��}�����L��X���,5�MH���Rd�N���&�C����8�i%^.c�Be�P���`�܃J!����2�iR���&�C��N��Qv˂�d���(oiː�l�#SSVnqv��@����:�h+��xX���bzSS�3� �M�5[����8�+�r/�����
|-/3�=��u\;�]׻{A��-�p�^1��!v��{�a���A��ߢh%��O��L�n76���'wR4E��A�~5уe����1
�Nе��t���:�Y��E�W S7�����v�&�xA���6�k{�Ɓ��zRԛ��D��u�d����Wkw_f�x��l����,*���p�Gp=����wCh~��w�k��Q+j�Ofn �y���^}g{��%p�7���y|y�<�l���t�c�\�M���LZ�I�ZЭ�?U��ۏ��������Ξ���У+/ߣ�Ѣ��E�(0���3�J�(�1�sZ<`?�04px�����	DQ���������BM�rtn���f:&c�������e$MVgX�h��WRGV��o������4�O?{�g=`���~|z�|��w��'�/?�,,^Еe������ae���<����>�g��q�=�}&������X��*y���s>��$0�9�����ΝO�QN̚*��~��Vx���l��l�ę���?}	!b0   i  Lga2/stage/AmpedScript; e i Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi��� mix2��� /efx/mix/bar8.agi��� Lkotori/gfx/KGLScriptModel; mix_m Lkotori/data/KGLGenerationData; glowD /efx/flash/glow_god.ptc�        �L��?ٙ�?ٙ�?L��              start icelance_shield_start broken icelance_shield_broken rebirth rebirth2 	finishKGL (Lkotori/gfx/KGLScriptModel;)V
 f x y generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d { |D (/accs/ice_lance/ice_lance_shield_mix.scm�            ?�  ?�  ?�               
setVisible (Z)V
 f  �
 H 0 3 setBlend
 d � 3 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � �@@   rotate (Lkotori/geom/Quaternion;)V
 f � ��� kotori.geom.Quaternion�;�.?�           A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d { �D /efx/ripple/ripple01.scm�            @@  @@  @@               
start_blu5 clearGenerate
 d � 1 setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/ice/freeze/freeze01.scm�            >���?   >L��        Cp   broken12D /efx/ice/freeze/freeze01.scm�    ����    >���?   >L��        C�  D /efx/ice/freeze/freeze01.scm�            >���?   >L��        C�  D /efx/flash/glow.scm�            ?�  ?�  >���              	iceafter3D /efx/flash/glow01_frz2.ptc�            ?�  ?�  >���             attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �� ga2.stage.atk.DiskAttack� � hit_i2  d� �� �    -            ��� �  d� �� �     kill
 f � 1 setEdge
 f � � removeMixture
 d � 1 setColor (FFFF)V
 d � � lockItemScript
 f � � 	(IFFFFF)V
 d � �CH   setRenderType
 d � 3 	fadeColor (FFFFI)V
 d � �D /efx/ripple/ripple01.scm�            @@  @@  @@               getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � m startScript (Ljava/lang/String;)Z
 � � � 
broken12_2D /efx/flash/glow.scm�            ?�33?�33>���             D /efx/flash/glow01_frz2.ptc�            ?�  ?�  >���             
stopScript
 f � 1D /efx/flash/glow.scm�        �L��?�33?�33>���            D /efx/flash/glow.scm�        ����?�33?�33>���            D /efx/flash/glow01_frz2.ptc�        �L��?�  ?�  >���            D /efx/flash/glow.scm�        �L��?�33?�33>���            D /efx/flash/glow.scm�        ����?�33?�33>���            D /efx/flash/glow01_frz2.ptc�        �L��?�  ?�  >���             f g  i h k h n m p o - 1  j�l�q��  r 1  *�� zW*~� }��  s 1  �*� �W*d� �W*� �W*� �W*�� �W*�� �W*�� �W*��� �W*� �W**��� �� �W**��� �� �W**��� �� �W*2� �W� � =*��� �W*�� }W*��� �W* �� �W*��� �W* �� �W� Y`� W� 2���*� �W�  / 1  *� �W*� �W*� �W*� �W�  t 1  }*� �W*�� �W*� �W*� �W* ȶ �W*� �W*��� �W� � *� � �¶ �W� Y`� W� ���*��� �W*Ķ }W*�� �W*� �W*� �W�  u 1  *� W�  v 1  I*� �W*��� �W*��� �W*ɶ }W*�� }W*�� }W*d� �W*� �W*d� �W*d� �W�  w 1  \*� �W*� �W*��� �W*��� �W*̶ }W*�� }W*�� }W*d� �W*� �W*d� �W*d� �W*� �W*� �W� �!��    ��( ���(>�->��>QYR�  