	� G */accs/ice_lance2/mdl/ice_lance2_shield.kmd ��       �V�o�D?�R%�+���`;N�V2>�mZ
m�Ұ�f9�$ŉ-{�Ͷ���Zā]>.\ �
$��TV��K��ĉ�73��$�.�EZ��]����{��9��hHРK�bu�\�{��o��#zNQ�x��n�q���Y\�}��IF�"=���ˁ���g���?xr�ӗ�d�%���E,��fw�C�]\��(����S�	�I�0�v��h�>�R�#Jǋ���B�!�n��x�^5kkoW��8G���+�=T`�Ҵ|M�:8O?5�,��:�|[���k@eU*�S����h�ózm<�,��]0�PHf�}8tqK�������q��H��@a�o$
K[�=1�X�(�F�cl��I3�b�4�ʁ��KǞ�j.I�^3��r�X��j�b�Q���23�2@�0DCl[N��R�
���rF��(��������S��0k��ɧ8=���i�{�%�����o���í(Q�}4�,
���/��c�ݏ�)���.��Z���mq��˖�qda���C����y�_j�O��W	�Uȳ��M砜Op�����@�\ʘΡt��W�>BG�+�/��	��R4o�����CC�Wn���]��#�5F֦��Р����*�&p���d�J�5P��.��k���U�o�ᱼ�"7ӛb۱���qܻ�4a�x2���]��;@61XSQ��&�/�{��h��#M������T�E�e�@͝PAX��A��� [��8},�q�-���Q�xu1	l�����Z�1 ���}�@���.�{�� 6}��h����*��c���о��`&�z�q�t@�C���iXmu�٤�Q�=DɰK��]��X`פ^�ܐ�ڽm��o%���i�^۩����F��Ɨ6�u@���=1|{������'/|���@'�� �{�:b�X���xl`If��0��lB7��)ʟ_x��7珞>;{�S�}�^�B�H���?-������*��˳U����}J�%v�����q)^��8XP�6-��	�ՙ��2�>jb�t'�*�)Ey~���s˗��ߘ=]�p��9�
D�_E���ѯ�i�>~���(|@O����߹�@�����s���zI�(v�����G��0
9��G�R�	��W�橄e
��]�_�/ݤ��.���t�d����Ǜ>�Ι�Y�,=�����ܫJU���JO�m��o#���   i  Lga2/stage/AmpedScript; e i Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi��� mix2��� /efx/mix/bar8.agi��� Lkotori/gfx/KGLScriptModel; mix_m Lkotori/data/KGLGenerationData; glowD /efx/flash/glow_god.ptc�        �L��?ٙ�?ٙ�?L��              start icelance_shield_start broken icelance_shield_broken rebirth rebirth2 	finishKGL (Lkotori/gfx/KGLScriptModel;)V
 f x y generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d { |D */accs/ice_lance2/ice_lance_shield2_mix.scm�            ?�  ?�  ?�               
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
 f � 1D /efx/flash/glow.scm�        �L��?�33?�33>���            D /efx/flash/glow.scm�        ����?�33?�33>���            D /efx/flash/glow01_frz2.ptc�        �L��?�  ?�  >���            D /efx/flash/glow.scm�        �L��?�33?�33>���            D /efx/flash/glow.scm�        ����?�33?�33>���            D /efx/flash/glow01_frz2.ptc�        �L��?�  ?�  >���             f g  i h k h n m p o - 1  j�l�q��  r 1  *�� zW*~� }��  s 1  �*� �W*d� �W*� �W*� �W*�� �W*�� �W*�� �W*��� �W*� �W**��� �� �W**��� �� �W**��� �� �W*2� �W� � =*��� �W*�� }W*��� �W* �� �W*��� �W* �� �W� Y`� W� 2���*� �W�  / 1  *� �W*� �W*� �W*� �W�  t 1  }*� �W*�� �W*� �W*� �W* ȶ �W*� �W*��� �W� � *� � �¶ �W� Y`� W� ���*��� �W*Ķ }W*�� �W*� �W*� �W�  u 1  *� W�  v 1  I*� �W*��� �W*��� �W*ɶ }W*�� }W*�� }W*d� �W*� �W*d� �W*d� �W�  w 1  \*� �W*� �W*��� �W*��� �W*̶ }W*�� }W*�� }W*d� �W*� �W*d� �W*d� �W*� �W*� �W� �!�_    ��( ���(>�->��>QYR�  