	� G #/efx/etc/efx_ninja/mdl/makibisi.kmd ��       �S[O�0~�C����┒8�`HY �����io�MNZ�I�N[���w��
{ڤ\|>��;��ai�L5���ӆO��,*HG����G�#QTR2�F*��p���' ����rݑp3��,(��?qw��j�[	C�4��k�&�X���A�}�u,cǇl飇?�*��D8x�S������7O����\=����ͥH����x%�6���еqZ{(�@����>���٬��t�캣��s�o���e�ASaZ�L�BO/�+@�QmL%���9��rv��2o`�*��#Y�)B��(n��z� �Cf�7�g9բ4۰����I\nOf��B��.�)E'�-~C̼0")/P)��"�;�b�Oc�X g#я�F6���5D�����U�Q5����y̢�6M���L����.f��8m����oM�h5��V!բ��7������ځw��u�J�Ð̕��Vɡ����y^Cã G�l���;m�|�y��JP� V����s�dX��ϔ�c����I�e�	ρnD*���yA�|2����������Ĭ��;��U�4�}�R�4yI���p �(@�m�_'�u�,���{<��T.F�I��ݴ����M,>HOD�n�#p��x�|ONl�����E/�<��L����
��y����׬�A�� �m7�w   D  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� 
showweapon show hit hitstage start p2 (F)F destroy timeout effect 
setMixture (Lkotori/data/MixtureData;)V
 d t u mixtureMotion (IFFF)V
 d w x�@  
 H 0 3 removeMixture
 d | 1 finishAllKGL
 f ~ 1 roll (Lkotori/geom/Vector3D;I)V
 f � �-             move (FFF)V
 f � ����� 	playSound
 d � 3 vibe (FFFFI)V
 d � ����
�#�
CH   kill
 f � 1-             attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            � 2� � hit_ba  d� !   �     � sin
 H � p@H��?���<o setShadowState
 d � 3-    �;�      Lkotori/geom/KMath; � D2R
 � � p cos
 H � p=��
?��� f h g
 - 1  i� �  j 1  *� � vW*z� yW*,� {W*� }W�  k 1  �  l 1  @*� W*� {W*�� �W*�� �W*� �W*��� ȶ �W*,� {W*� �W�  m 1  H*� W*� {W*�� �W*� �W*� �W*��� ȶ �W*��� �W*ж {W*� �W�  n 1  6DEF� '*#�j� �� *#�j� �� jE*
� {W#�bD���*� �W�  o p  ##j�  q 1  �  r 1  *� �W�  s 1  @*� �W*�� �W<� $*���j� �� �j� �W*
� {W�W<���*� �W� �Tt�            =���<���=����  