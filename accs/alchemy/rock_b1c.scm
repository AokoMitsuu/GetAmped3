	� G /efx/rock/rock_stand1.mot�       �XKo�F>+@�=�
PK���t�mEqbǅ��+rE��ʖc�`9�(�"-P4@�R��h������R�L?k�V3;;�ٙ�f��<"8��A���`��G�޲b7"��׮_s�(��4I������3�D�A��N�A�l�����[TnYЙ.�YwGd��"��HjH7r$��w�Z�m��կ:6[�����rV(<j���7��h��bCE�[���/
B���&4u�.��`	z]j���`�~fѽ�Z8F�f��Ek��AҶ�wR��Cbתz��'�8ڢ�X~�.Ea"k
,�h���X�ò�0� ��!/�u���ʞ��~�Nƪ:^���f��_����X+��?ۡkK�1�nN0y�&.xNfS+�u^hV	�y��$bE�OƥR�#,k�BM���$�uM�0u�S~�Yb��v��a`ˆ�hr{�&��*8�q�^�yAS��Pr�F,3e�Tf+���>8fm���tb�B'�P`I��׬��4E�l
��ݡ��8 �(�Cd�;(v0�W���>_��r�����,k�{���H�'{�/%,ۇL����'\�l e��ML���a6ۼ���ھ��%�����ֺ�bnܾ-Xˡo���4��S]p[��ӓ�5]P���{�P�5e��I�-�CS2qБ�$�����zH�U�rcKє�AF+�.o�ml���+-��?s�ȃj�������������z���iqw��׏���W��^����R^P%j?�ʠĒ�E���*�W6�.��Z舑0�?~5>�eJ�UǙ3Z��o~{ɋϱu�O�!�t(9�m!���4;ј������l�O���*|Iu�z���y���OL�
���P��;Ȍ���<u�-[����%�ț��bdj�Q��R��+���Q��P�tF��+&|G߿x>>����/Ƈ��M-|*��.�g������0� �YΘs" :|���Sa��?���π�"s] \ʺ⍎V	�%��A�n��ɠ�9�_���6�!:�m�%�5��k:�#d��k >�0��>�Z�����Ng�l���S3.fâ:cD�.`�d�;��O��13o��bf�6�BaU���ݯ�h��<Zl��� �T��}��n�+��&��Y��� @H�6�pB@X�����,YvL�/w�0�]a'�4�S����L�����S�������������.�l]@��1{CƇ��<y:>|:>x��T� �z��\����x&� Q�����SP�N�E����/�sa�jԲ����P���G���s������1�+Apƞh	���%f��+�iv�� ~d��υs�����D�?�yP��y�j0N���14DL����0$��͛���y� ��J��s��N��GN�SW9驹��IVѦ_Y��A�ªs�7��ܘ]f����N�(p��ް�AE�,�	cO��!��ai��	���I+�1�؉��gϲ���<!��\g$$�R!V����ă   �  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi���� Lkotori/data/KGLGenerationData; elecD /efx/electric/lightning08.scm�    ?��    ?���?���?���              flashD /efx/flash/glow01_blu19.ptc�    ?��    @�  @�  @�                start approachtarget 	damagehit 	lightning destroy broken 
setVisible (Z)V
 f u v lockItemScript (ZZ)V
 f x y size (FFF)V
 f { |?@  
 H 0 3 tempInt (I)Ljava/lang/Integer;
 d � � 	playSound
 d � 3D /efx/electric/rnd_thunder.scm�    >��    ?   ?   ?                 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � start5 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D #/efx/shockwave/shockwave_ground.scm�    >��    ?@  ?@  ?@                setJudgmentApproachTarget (F)V
 f � �?�� Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 setRenderType
 d � 3 setBlend
 d � 3 setColor (FFFF)V
 d � �>�ff>���>8Q� setEdge
 f � v anime (FFFZ)V
 d � �B    (II)V
 d � � 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � ���   	(IFFFFF)V
 d � �C�  D /efx/electric/rnd_thunder.scm�    >��    ?�  ?�  ?�                rndc (F)F
 H � � j hpb
 � � |C4   start_lb getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � Lkotori/gfx/KGLScriptModel; � kill
 � � 1D /efx/stage/iwa_r/iwa_r.scm�            @   >���@   Bp            crack5_2D /efx/stage/iwa_r/iwa_r.scm�            @   >���@   C             clearGenerate
 d � 1 setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/rock/rock01.scm�>���    �L��?���?���?���              start5_3D /efx/smoke/smoke_sand11.ptc�    ?L��    ?�  ?�  ?�               D /efx/smoke/smoke_sand11.ptc�    ?L��    ?�  ?�  ?�                attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-    ?��    �� ga2.stage.atk.DiskAttackx� � 	hit_goza2  d� XX �    -    ?��    ��F� � hit_bax d� XX �     vibe (FFFFI)V
 d � �<�G�P  yquake
 d � 3 setJudgmentKind
 f � 3 enableCollision
 f � v�    startScript (Ljava/lang/String;)Z
 � � � mix_blueD /efx/electric/rnd_thunder.scm�    >��    ?@  ?@  ?@               D /efx/electric/rnd_thunder.scm�    >��    ?@  ?@  ?@                clearJudgmentKind
 f � 1 lockTimeCount
 f � v@@  @�   temp ()Ljava/lang/Object;
 d � � Ljava/lang/Integer; � intValue
 � � 2 quake
 d � 3D /efx/smoke/smoke_sand11.ptc�    ?L��    ?�  ?�  ?�               D /efx/smoke/smoke_sand11.ptc�    ?L��    ?�  ?�  ?�               D /efx/electric/lightning08.scm�    ?��    ?�  ?�  ?�               D /efx/flash/glow01_blu19.ptc�    ?��    @�  @�  @�                	fadeColor
 d � � 	crack_end
 f � 1 
stopScript
 f � 1 f h g k j m j - 1  i� l�n��  o 1  {*� wW*� zW*~~~� }W*d� W*� �W*� �W�L*+�� �W*�� �W*�� W*� zW*�� �W*� W� * ȶ �W*+�� �W*ܶ W���  p 1 V*� wW*� �W*� �W*���� �W*� �W*� �W*�� �W*%� �W* �� �W*� � �W*�� �W*�� �W*��� �W��� ��� ��� �� �W*��� �W��� ��� ��� �� �W*��� �W*�� �W*�� �W*� �L*� �M+� +� �W,� ,� �W*�¶ �L*�¶ �M*� �W*+� �W*,� �W*�ʶ �W*˶ �W*̶ �W*�Ѷ �W*�Ӷ �W*2� W*��,� �W*� �W*� �W*� �W*�� W*� W�  q 1  (*�� ȶ �W*� �W* ȶ W*� �W*� W�  r 1 k*� �L*� �M�[* ȶ �W*� � �W*�� �W*�� �W+� 
+� �W,� 
,� �W��� ��� ��� �� �W*��� �W*�� �W*�� �W*��� �W*� W*� � �W*�� �W*�� �W+� 
+� �W,� 
,� �W��� ��� ��� �� �W*��� �W*�� �W*�� �W* �� W*� � �W*�� �W*�� �W+� 
+� �W,� 
,� �W��� ��� ��� �� �W*��� �W*�� �W*�� �W*��� �W*ܶ W����  s 1  *� W�  t 1 F*� �W*� �W*� zW*� �W*���� �W*��,� �W*� � �W*�� �W*�� �W*� �L*� �M*� �N-� �-� �� �*� �W*%� �W*� �W+� 
+� �W,� 
,� �W*�� �W*�� �W�:�� ��� ��� �� �W*�� �W�� ��� ��� �� �W*�� �W�:*� �W*� �W*�� W*� �W*�� �W*� �W+� 
+�� �W,� 
,�� �W*�� W*� �W*� �W� �a    ?&ff    ?�?&ff?�� � 
HP_nojudge�BH  �	 	collision�
1    ?�R    >�ff?�R>�ff